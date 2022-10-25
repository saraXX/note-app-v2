package android.guide.note_app_v2.fragments

import android.guide.note_app_v2.R
import android.guide.note_app_v2.databinding.FragmentAddNoteBinding
import android.guide.note_app_v2.model.Note
import android.guide.note_app_v2.viewmodel.NoteViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController


class AddNoteFragment : Fragment() {
    val TAG = "AddNoteFragment"
    private var binding: FragmentAddNoteBinding? = null
    private val sharedViewModel : NoteViewModel by activityViewModels()

    var editTitle = ""
    var editBody = ""



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentAddNoteBinding.inflate(inflater,container,false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            addNoteFrag = this@AddNoteFragment

            if(sharedViewModel.idx.value!! > -1) {
                Log.d(TAG, "onViewCreated: idx is "+sharedViewModel.idx.value)
                editingUI()
            }
        }
    }

    fun editingUI(){
        binding?.apply {
            deleteBtn.visibility = View.VISIBLE
            editTitle = sharedViewModel.noteList.value?.get(sharedViewModel.idx.value!!)?.noteTitle.toString()
            editBody = sharedViewModel.noteList.value?.get(sharedViewModel.idx.value!!)?.noteBody.toString()
        }
    }

    fun saveBtn(){
        var newTitle = ""
        var newBody = ""

        binding?.apply {
            newTitle = titleEditTextView.text.toString()
            newBody = bodyEditTextView.text.toString()
        }

        if (sharedViewModel.idx.value==-1){
            sharedViewModel.createNote(Note(newTitle,newBody,""))
        } else{
            sharedViewModel.editNote(Note(newTitle,newBody,""), sharedViewModel.idx.value!!)
        }
        toListFragment()
    }

    fun deleteBtn(){
        sharedViewModel.deleteNote(sharedViewModel.idx.value!!)
        toListFragment()
    }
    fun cancelBtn(){
        toListFragment()
    }
    fun toListFragment(){
        findNavController().navigate(R.id.action_addNoteFragment_to_noteListFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}