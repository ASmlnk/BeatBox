package combignerdranch.android.beatbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import combignerdranch.android.beatbox.databinding.ActivityMainBinding
import combignerdranch.android.beatbox.databinding.ListItemSoundBinding

class MainActivity : AppCompatActivity() {

    private lateinit var beatBox: BeatBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        beatBox = BeatBox(assets)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = SoundAdapter(beatBox.sounds)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        beatBox.release()
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

