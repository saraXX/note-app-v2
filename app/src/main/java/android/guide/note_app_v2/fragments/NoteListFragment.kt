package android.guide.note_app_v2.fragments

import android.guide.note_app_v2.R
import android.guide.note_app_v2.adapter.NoteAdapter
import android.guide.note_app_v2.databinding.FragmentNoteListBinding
import android.guide.note_app_v2.viewmodel.NoteViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

class NoteListFragment : Fragment() {

    private val TAG = "NoteListFragment"
    private var binding: FragmentNoteListBinding? = null
    private val sharedViewModel : NoteViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentNoteListBinding.inflate(inflater,container,false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            noteListFrag = this@NoteListFragment

            noteRecyclerView.layoutManager = LinearLayoutManager(context)
            noteRecyclerView.adapter = NoteAdapter(context, sharedViewModel)
        }

    }

    fun createNote(){
        sharedViewModel.setIndex(-1)
         // pass -1 to that this for create a new note, not for updating or deleting a note
        findNavController().navigate(R.id.action_noteListFragment_to_addNoteFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}