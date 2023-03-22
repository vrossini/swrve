package com.example.swrve;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import android.app.Application;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.swrve.database.AppDatabase;
import com.example.swrve.models.Image;
import com.example.swrve.util.Utils;
import com.example.swrve.viewmodels.MainViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class DownloadFlowTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private final AppDatabase mDb = mock(AppDatabase.class);
    private final Application application = mock(Application.class);

    private MainViewModel mainViewModel;

    private String URI = "https://i.imgur.com/DvpvklR.png";

    @Before
    public void setupDownloadFlowTest() {
        MockitoAnnotations.initMocks(this);
        mainViewModel = new MainViewModel(application);
        mainViewModel.url.setValue(URI);
        mainViewModel.insertImage(new Image(URI));
        mainViewModel.insertSuccessful.setValue(0L);
    }

    @Test
    public void urlValidator_ReturnsTrue() {
        try {
            assertTrue(Utils.isValidURL(mainViewModel.url.getValue()));
        } catch (MalformedURLException | URISyntaxException e) {
            assertNull(e);
        }
    }

}
