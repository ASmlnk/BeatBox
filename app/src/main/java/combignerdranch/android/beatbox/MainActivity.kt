package combignerdranch.android.beatbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import combignerdranch.android.beatbox.databinding.ActivityMainBinding
import combignerdranch.android.beatbox.databinding.ListItemSoundBinding

class MainActivity : AppCompatActivity() {


    private lateinit var beatBox: BeatBox
    private val beatBoxViewModel: BeatBoxViewModel by lazy {
        ViewModelProvider(this) [BeatBoxViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //beatBox = BeatBox(assets)

            beatBox = beatBoxViewModel.beatBox



        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
         binding.textViewBar.text = "Playback Speed 0%"

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = SoundAdapter(beatBox.sounds)
        }

        binding.seekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    val processRatePlay: Float = 1.0f + progress / 100f
                    beatBox.ratePlay = processRatePlay
                    binding.textViewBar.text = "Playback Speed $progress%"
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {

                }
            }
        )

    }

    override fun onDestroy() {
        super.onDestroy()
        //beatBox.release()
    }

    private inner class SoundHolder (private val binding: ListItemSoundBinding) :
            RecyclerView.ViewHolder(binding.root) {

        /*тут подключаем нашу модель представления.
        * для начала делаем подключение в холдере потом в адаптере*/

                init {
                    binding.viewModel = SoundViewModel(beatBox) //подключение нашей модели представления
                }

        fun bind(sound: Sound) {
            /*В функцию Bind холдера добавляем наш обьект sound*/
            binding.apply {
                viewModel?.sound = sound  //присваиваем sound свойству обьекта SoundViewModel()
                executePendingBindings() //Вызов приказывает макету обновить себя немедлено
            }
        }

            }
    private inner class SoundAdapter(private val sounds: List<Sound>) : RecyclerView.Adapter<SoundHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {

            val binding = DataBindingUtil.inflate<ListItemSoundBinding>(
                layoutInflater,
                R.layout.list_item_sound,
                parent,
                false)

            return SoundHolder(binding)
        }

        override fun onBindViewHolder(holder: SoundHolder, position: Int) {
           //завершаем подключение нашей  модели представления
            val sound = sounds[position]
            holder.bind(sound)

        }

        override fun getItemCount(): Int = sounds.size

    }
}

