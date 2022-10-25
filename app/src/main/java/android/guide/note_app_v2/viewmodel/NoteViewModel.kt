package android.guide.note_app_v2.viewmodel

import android.guide.note_app_v2.model.Note
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class NoteViewModel : ViewModel() {
    private val TAG = "NoteViewModel"

    private object DataSource{
        val noteList = mutableListOf<Note>()
    }

    private val _noteList = MutableLiveData(DataSource.noteList)
    val noteList : LiveData<MutableList<Note>> = _noteList

    private val _idx = MutableLiveData<Int>()
    val idx : LiveData<Int> = _idx



    init {
        initValue()
    }

    fun createNote(note: Note){
        _noteList.value?.add(note)
    }
    fun editNote(note: Note, idx: Int){
        _noteList.value?.set(idx, note)
    }
    fun deleteNote(idx: Int){
        _noteList.value?.removeAt(idx)
    }

    fun setIndex(index: Int){
        _idx.value = index
    }



    fun initValue(){
        val n = Note("22","11","21")
        _noteList.value?.add(n)

//        DataSource.noteList.add(Note("82","31","214"))
    }


}


