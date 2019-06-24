package com.example.retrofitfirstattempt.db;


import android.arch.lifecycle.LiveData;
import android.support.test.runner.AndroidJUnit4;

import com.example.retrofitfirstattempt.model.RetroPhoto;
import com.example.retrofitfirstattempt.utils.LiveDataTestUtil;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class RetroPhotoDaoTest extends DBTest {

    /**
     * Tests the db on memory
     * @throws
     */
    @Test
    public void insertAndLoad() throws InterruptedException {
        RetroPhotoDao dao = db.retroPhotoDao();
        final RetroPhoto[] photos = {new RetroPhoto(101, 1, "photo1title", "url", "thumbnailUrl", new Date()),
                new RetroPhoto(101, 2, "photo2title", "url", "thumbnailUrl", new Date()),
                new RetroPhoto(101, 3, "photo3title", "url", "thumbnailUrl", new Date())};
        dao.saveVarious(photos);

        LiveData<List<RetroPhoto>> retrievedPhotos = dao.findPhotosByNameAndAlbumInRoom("pho",101);//LiveDataTestUtil.getValue(db.retroPhotoDao().findPhotosByNameAndAlbumInRoom("",101));
        assert(LiveDataTestUtil.getValue(retrievedPhotos).get(0).getTitle().compareTo("photo1title") == 0);
    }

    /*@Test
    public void makeDatabase() {
        initDb();
        //assert(db!=null);
        RetroPhotoDao dao = db.retroPhotoDao();
        //assert(dao != null);
        final RetroPhoto[] photos = {new RetroPhoto(101, 1, "photo1title", "url", "thumbnailUrl", new Date()),
                new RetroPhoto(101, 2, "photo2title", "url", "thumbnailUrl", new Date()),
                new RetroPhoto(101, 3, "photo3title", "url", "thumbnailUrl", new Date())};
        dao.saveVarious(photos);
        LiveData<List<RetroPhoto>> liveDataResult = dao.findPhotosByNameAndAlbumInRoom("pho",101);
        //assert(liveDataResult != null);
        List<RetroPhoto> list = liveDataResult.getValue();
        //assert(list != null);
        assert(list.get(0).getTitle().compareTo("photo1title") == 0);
        closeDb();
    }*/
}
