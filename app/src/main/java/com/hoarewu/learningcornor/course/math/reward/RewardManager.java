package com.hoarewu.learningcornor.course.math.reward;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by danielrosowski on 22.07.16.
 */
public class RewardManager {

    public static final String UNLOCKED_IMAGES = "unlocked_images";
    public static final String REWARD_PREF = "reward_pref";
    public static final String ANIMALS = "animals/";

    private final SharedPreferences sharedPref;

    public RewardManager(Context appContext) {
        this.sharedPref = appContext.getSharedPreferences(REWARD_PREF, Context.MODE_PRIVATE);
    }

    // test constructor
    RewardManager(SharedPreferences sharedPref) {
        this.sharedPref = sharedPref;
    }

    public Drawable unlockReward(AssetManager assets) throws IOException {
        List<String> allFiles = Arrays.asList(assets.list("animals"));
        String unlockedString = sharedPref.getString(UNLOCKED_IMAGES, "");
        List<String> lockedFiles = filterAlreadyUnlocked(unlockedString, allFiles);

        Random random = new Random();
        int index = random.nextInt(lockedFiles.size());
        SharedPreferences.Editor edit = sharedPref.edit();
        if (unlockedString.isEmpty()) {
            edit.putString(UNLOCKED_IMAGES, "" + index);
        } else {
            edit.putString(UNLOCKED_IMAGES, unlockedString + "," + index);
        }
        edit.apply();
        return Drawable.createFromStream(assets.open(ANIMALS + lockedFiles.get(index)), null);
    }

    public List<Drawable> loadUnlockedRewards(AssetManager assets) throws IOException {
        List<String> allFiles = Arrays.asList(assets.list("animals"));
        String unlockedString = sharedPref.getString(UNLOCKED_IMAGES, "");
        List<String> unlockedFiles = filterFileList(unlockedString, allFiles, FilterType.KEEP_UNLOCKED);
        List<Drawable> rewards = new ArrayList<>(unlockedFiles.size());
        for(String filePath : unlockedFiles) {
            rewards.add(Drawable.createFromStream(assets.open(ANIMALS + filePath), null));
        }
        return rewards;
    }

    private List<String> filterAlreadyUnlocked(String unlockedString, List<String> allFiles) {
        return filterFileList(unlockedString, allFiles, FilterType.KEEP_LOCKED);
    }

    private List<String> filterFileList(String unlockedString, List<String> allFiles, FilterType type) {
        String[] tokens = unlockedString.split(",");
        List<Integer> alreadyUnlocked = new ArrayList<>(tokens.length);
        for (int i = 0; i < tokens.length; i++) {
            if (!tokens[i].isEmpty()) {
                alreadyUnlocked.add(Integer.valueOf(tokens[i]));
            }
        }

        ArrayList<String> filteredFiles;
        if (type.equals(FilterType.KEEP_LOCKED)) filteredFiles = Lists.newArrayList(allFiles);
        else filteredFiles = Lists.newArrayList();

        for (Integer index : alreadyUnlocked) {
            if (type.equals(FilterType.KEEP_LOCKED)) {
                filteredFiles.remove(index);
            } else {
                filteredFiles.add(allFiles.get(index));
            }
        }

        return filteredFiles;
    }

    private static enum FilterType {
        KEEP_UNLOCKED, KEEP_LOCKED;
    }
}
