package ipca.gestorpasswords.passwordgestor

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import ipca.gestorpasswords.passwordgestor.room.PasswordEntity
import kotlinx.android.synthetic.main.activity_password_layout.view.*

class PasswordAdapter(val passwords : List<PasswordEntity>):RecyclerView.Adapter<PasswordAdapter.PasswordViewHolder>(){

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): PasswordViewHolder{
        return PasswordViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_password_layout, parent , false)
        )
    }

    override fun getItemCount() = passwords.size


    override fun onBindViewHolder(holder: PasswordViewHolder, position : Int){
        holder.view.textViewPassword.text = passwords[position].password

        holder.view.setOnClickListener{
            val action = MainFragmentDirections.actionAddPassword()
            action.password = passwords[position]
            Navigation.findNavController(it).navigate(action)
        }
    }

    class PasswordViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}