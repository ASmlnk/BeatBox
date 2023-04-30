package combignerdranch.android.beatbox

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class SoundViewModel : BaseObservable() {
    /*Выполняет роль интерфейса для адаптера (это модель представления)
    * Наследуемся от класса BaseObservable() для установления слушателя для модели представления
    * что бы наблюдать за изменениями в макете(например при прокрутке ресайзвиев)*/

    var sound: Sound? = null
        set(sound) {
            field = sound
            notifyChange()

        /*notifyChange() эта фугкция оповещает класс привязки о том что все Bindable- свойства
            * наших обьектов были обнавлены. Класс привязки выполнит код внутри скобок {} для повторного
            * заполнения представления.*/
    }

    /*ДЛя BaseObservable() помечаем свойство используемое в привязке анатацией @get:Bindable*/
    @get:Bindable
    val title: String?
        get() = sound?.name

    /*Функция для получения названия, которое должно отображаться на кнопке */
}