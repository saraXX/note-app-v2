package android.guide.note_app_v2.adapter

import android.content.Context
import android.guide.note_app_v2.R
import android.guide.note_app_v2.viewmodel.NoteViewModel
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(
    private val context: Context?, val sharedViewModel: NoteViewModel) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private val TAG = "NoteAdapter"
    private val size : Int = sharedViewModel.noteList.value?.size!!
//    private val index: I

    class NoteViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        val noteTitleView: TextView = view.findViewById(R.id.title_itemView)
        val noteBodyView: TextView = view.findViewById(R.id.body_itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val adapterLayout = LayoutInflater.from(context)
            .inflate(R.layout.note_list_item,parent,false)

        return NoteViewHolder(adapterLayout)
    }


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val noteItem = sharedViewModel.noteList.value?.get(position)

        holder.noteTitleView.text = noteItem?.noteTitle
        holder.noteBodyView.text = noteItem?.noteBody

        holder.view.setOnClickListener {
            sharedViewModel.setIndex(position)
            holder.view.findNavController().navigate(R.id.action_noteListFragment_to_addNoteFragment)
        }

    }

    override fun getItemCount() = size

}