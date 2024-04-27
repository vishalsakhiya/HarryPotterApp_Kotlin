package com.example.harrypotterapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.harrypotterapp.CharacterDetailActivity
import com.example.harrypotterapp.R
import com.example.harrypotterapp.api.HowardResponse

class HowardListAdapter: RecyclerView.Adapter<HowardListAdapter.HomeViewHolder>() {
    private var data : ArrayList<HowardResponse>?=null
    private var context: Context?=null

    fun setData(list: ArrayList<HowardResponse>) {
        data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        context = parent.context;
        return HomeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.howard_rv_item_view, parent, false))
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = data?.get(position)
        holder.bindView(item, context)
    }

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: HowardResponse?, context: Context?) {
            val txtTitle: TextView = itemView.findViewById(R.id.tv_home_item_title)
            txtTitle.text = item?.name
            val txtBody: TextView = itemView.findViewById(R.id.tv_home_item_body)
            txtBody.text = item?.actor
            val imgProfile: ImageView = itemView.findViewById(R.id.imgProfile)

            var requestOptions = RequestOptions()
            requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(16))
            Glide.with(context!!)
                .load(item?.image)
                .placeholder(R.drawable.user)
                .apply(requestOptions)
                .into(imgProfile)

            itemView.setOnClickListener(View.OnClickListener {
                val characterIntent = Intent(context, CharacterDetailActivity::class.java)
                characterIntent.putExtra("characterId", item?.id);
                context.startActivity(characterIntent)
            })
        }
    }
}