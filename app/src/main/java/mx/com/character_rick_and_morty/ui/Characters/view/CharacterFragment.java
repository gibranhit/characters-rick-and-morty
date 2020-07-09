package mx.com.character_rick_and_morty.ui.Characters.view;

import android.app.Dialog;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import mx.com.character_rick_and_morty.R;
import mx.com.character_rick_and_morty.base.BaseFragment;
import mx.com.character_rick_and_morty.dependecies.di.AppComponent;
import mx.com.character_rick_and_morty.dependecies.rest.response.CharacterResponse;
import mx.com.character_rick_and_morty.dependecies.rest.response.model.Info;
import mx.com.character_rick_and_morty.dependecies.rest.response.model.Result;
import mx.com.character_rick_and_morty.ui.Characters.adapter.CharacterAdapter;
import mx.com.character_rick_and_morty.ui.Characters.di.CharacterModule;
import mx.com.character_rick_and_morty.ui.Characters.di.DaggerCharacterComponent;

import static mx.com.character_rick_and_morty.dependecies.rest.endpoints.ApiConstants.BASE_URL;
import static mx.com.character_rick_and_morty.dependecies.rest.endpoints.ApiConstants.CHARACTER;
import static mx.com.character_rick_and_morty.utils.AndroidUtils.configProgressBar;

public class CharacterFragment extends BaseFragment implements CharacterContract.View {

    @Inject
    CharacterContract.Presenter presenter;

    @BindView(R.id.rv_characters)
    protected RecyclerView rvCharacters;

    @BindView(R.id.chip_group_gender)
    protected ChipGroup chipGroupGender;

    @BindView(R.id.chip_group_specie)
    protected ChipGroup chipGroupSpecie;

    @BindView(R.id.chip_group_status)
    protected ChipGroup chipGroupStatus;

    @BindView(R.id.btn_back)
    protected TextView btnBack;

    @BindView(R.id.btn_forward)
    protected TextView btnForward;

    private Info info;

    private Dialog dialog;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_character;
    }

    @Override
    protected void initUI() {
        setUpProgressBar();
        presenter.getCharacterRickAndMorty(BASE_URL + CHARACTER);
    }

    private void setUpProgressBar() {
        if (dialog == null)
            dialog = configProgressBar(activity);
    }

    @OnClick(R.id.btn_search_character)
    protected void searchCharacter() {
        String query = getTag(chipGroupSpecie) +"&"+ getTag(chipGroupStatus) +"&"+ getTag(chipGroupGender);
        presenter.getCharacterRickAndMorty(BASE_URL + CHARACTER + query);
    }

    private String getTag(ChipGroup chipGroup) {
        String tag = "";
        for (int i = 0;  i < chipGroup.getChildCount(); i++){
            Chip chip = (Chip) chipGroup.getChildAt(i);
            if (chip.isChecked()){
                tag = chip.getTag().toString();
            }
        }
        return tag;
    }

    @Override
    protected void setUpComponent(AppComponent appComponent) {
        DaggerCharacterComponent.builder()
                .appComponent(appComponent)
                .characterModule(new CharacterModule(this))
                .build().inject(this);
    }

    @Override
    public void onSuccessCharacter(CharacterResponse response) {
        setUpListCharacter(response.getResults());
        btnBack.setEnabled(response.getInfo().getPrev() != null);
        btnForward.setEnabled(response.getInfo().getNext() != null);
        info = response.getInfo();
    }

    @Override
    public void onFailCharacter(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog() {
        dialog.show();
    }

    @Override
    public void hideDialog() {
        dialog.dismiss();
    }

    private void setUpListCharacter(List<Result> results){
        CharacterAdapter adapter = new CharacterAdapter(results, activity);
        rvCharacters.setAdapter(adapter);
        rvCharacters.setLayoutManager(new LinearLayoutManager(activity));
        rvCharacters.setHasFixedSize(true);
    }

    @OnClick(R.id.btn_back)
    protected void previousPage(){
        presenter.getCharacterRickAndMorty(info.getPrev());
    }

    @OnClick(R.id.btn_forward)
    protected void nextPage(){
        presenter.getCharacterRickAndMorty(info.getNext());
    }
}