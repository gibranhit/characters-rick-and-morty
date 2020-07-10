package mx.com.character_rick_and_morty.ui.characters.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mx.com.character_rick_and_morty.dependecies.rest.callback.ResponseCallBack;
import mx.com.character_rick_and_morty.dependecies.rest.response.CharacterResponse;
import mx.com.character_rick_and_morty.ui.characters.interactor.CharacterInteractor;
import mx.com.character_rick_and_morty.ui.characters.view.CharacterContract;

import static mx.com.character_rick_and_morty.dependecies.rest.endpoints.ApiConstants.BASE_URL;
import static mx.com.character_rick_and_morty.dependecies.rest.endpoints.ApiConstants.CHARACTER;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

public class CharacterPresenterTest {

    @Mock
    private CharacterContract.View mView;

    @Mock
    private CharacterInteractor mInteractor;

    private CharacterPresenter mPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mPresenter = new CharacterPresenter(mView, mInteractor);
    }

    @Test
    public void getListCharacterRequestSuccess(){
        doAnswer(invocation -> {
            ResponseCallBack callBack = invocation.getArgument(1);
            callBack.onSuccess(any(CharacterResponse.class));
            return callBack;
        }).when(mInteractor).getCharacterRickAndMorty(eq(BASE_URL + CHARACTER), any());
        mPresenter.getCharacterRickAndMorty(BASE_URL + CHARACTER);
        verify(mView).onSuccessCharacter(any());
    }

    @Test
    public void getListCharacterRequestFail(){
        doAnswer(invocation -> {
            ResponseCallBack callBack = invocation.getArgument(1);
            callBack.onFail(any());
            return callBack;
        }).when(mInteractor).getCharacterRickAndMorty(eq(BASE_URL + CHARACTER), any());
        mPresenter.getCharacterRickAndMorty(BASE_URL + CHARACTER);
        verify(mView).onFailCharacter(any());
    }
}