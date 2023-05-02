package combignerdranch.android.beatbox

import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


class SoundViewModelTest {

    private lateinit var beatBox: BeatBox
    private lateinit var sound: Sound
    private lateinit var subject: SoundViewModel

    @Before
    fun setUp() {
        beatBox = mock(BeatBox::class.java)
        /* beatBox это заглушка для имитациии класса BeatBox*/
        sound = Sound("assetPath")
        subject = SoundViewModel(beatBox) //тестируемый обьект
        subject.sound = sound
    }

    @Test
    fun exposesSoundNameAsTitle() {
        assertSame(subject.title, (sound.name))  // обратная кавычка
    }

    /*@Test
    fun callsBeatBoxPlayOnButtonClicked() {
        subject.onButtonClicked()

        verify(beatBox).play(sound)
        *//* verify(object) объекта Mockito проверяет вызывалась эта функция как мы ожидали
        * Вызов verify(beatBox) означает: «Я хочу проверить, что для beatBox была вызвана функция»
        *  Таким образом, вызов verify(...) означает: «Проверить, что функция play(...) была вызвана для beatBox с передачей
            sound в качестве параметра»
        * *//*
    }*/

}

















