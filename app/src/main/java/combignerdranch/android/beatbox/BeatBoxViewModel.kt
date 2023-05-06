package combignerdranch.android.beatbox

import android.app.Application
import android.content.res.AssetManager
import android.view.ContextThemeWrapper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

class BeatBoxViewModel(application: Application): AndroidViewModel(application) {

    var beatBox: BeatBox = BeatBox(application.assets)



}