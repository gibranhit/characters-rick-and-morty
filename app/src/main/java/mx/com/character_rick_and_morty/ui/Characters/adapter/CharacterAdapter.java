package mx.com.character_rick_and_morty.ui.Characters.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.com.character_rick_and_morty.R;
import mx.com.character_rick_and_morty.dependecies.rest.response.model.Result;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private List<Result> results;
    private Context context;

    public CharacterAdapter(List<Result> results, Context context) {
        this.results = results;
        this.context = context;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CharacterViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_character, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        Result result = results.get(position);
        holder.tvName.setText(result.getName());
        Glide.with(context).load(result.getImage()).centerCrop().into(holder.ivImageCharacter);
        int icon = getStatus(result.getStatus());
        holder.tvStatusSpecie.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0);
        String statusAndSpecie = result.getStatus() + " - " + result.getSpecies();
        holder.tvStatusSpecie.setText(statusAndSpecie);
        holder.tvFirstLocation.setText(result.getOrigin().getName());
        holder.tvLastLocation.setText(result.getLocation().getName());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    private int getStatus(String status){
        int statusImage;
        switch (status){
            case"Alive":
                statusImage = R.drawable.ic_alive;
                break;
            case"Dead":
                statusImage = R.drawable.ic_dead;
                break;
            default:
                statusImage = R.drawable.ic_unknown;
                break;
        }
        return statusImage;
    }

    public class CharacterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_image_character)
        protected ImageView ivImageCharacter;

        @BindView(R.id.tv_name)
        protected TextView tvName;

        @BindView(R.id.tv_status_and_specie)
        protected TextView tvStatusSpecie;

        @BindView(R.id.tv_last_location_description)
        protected TextView tvLastLocation;

        @BindView(R.id.tv_first_location_description)
        protected TextView tvFirstLocation;


        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
