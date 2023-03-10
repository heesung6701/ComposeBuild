/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.androidx.media;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.SystemClock;

import androidx.mediarouter.media.MediaItemStatus;

/**
 * PlaylistItem helps keep track of the current status of an media item.
 */
final class PlaylistItem {
    // immutables
    private final String mSessionId;
    private final String mItemId;
    private final Uri mUri;
    private final String mMime;
    private final String mTitle;
    private final PendingIntent mUpdateReceiver;
    // changeable states
    private int mPlaybackState = MediaItemStatus.PLAYBACK_STATE_PENDING;
    private long mContentPosition;
    private long mContentDuration;
    private long mTimestamp;
    private String mRemoteItemId;

    PlaylistItem(PlaylistItem item) {
        mSessionId = item.mSessionId;
        mItemId = item.mItemId;
        mTitle = item.mTitle;
        mUri = item.mUri;
        mMime = item.mMime;
        mUpdateReceiver = null;

        mPlaybackState = item.mPlaybackState;
        mContentPosition = item.mContentPosition;
        mContentDuration = item.mContentDuration;
        mTimestamp = item.mTimestamp;
        mRemoteItemId = item.mRemoteItemId;
    }

    public PlaylistItem(String qid, String iid, String title, Uri uri, String mime,
            PendingIntent pi) {
        mSessionId = qid;
        mItemId = iid;
        mTitle = title;
        mUri = uri;
        mMime = mime;
        mUpdateReceiver = pi;
        setTimestamp(SystemClock.elapsedRealtime());
    }

    public void setRemoteItemId(String riid) {
        mRemoteItemId = riid;
    }

    public void setState(int state) {
        mPlaybackState = state;
    }

    public void setPosition(long pos) {
        mContentPosition = pos;
    }

    public void setTimestamp(long ts) {
        mTimestamp = ts;
    }

    public void setDuration(long duration) {
        mContentDuration = duration;
    }

    public String getSessionId() {
        return mSessionId;
    }

    public String getItemId() {
        return mItemId;
    }

    public String getRemoteItemId() {
        return mRemoteItemId;
    }

    public String getTitle() {
        return mTitle;
    }

    public Uri getUri() {
        return mUri;
    }

    public String getMime() {
        return mMime;
    }

    public PendingIntent getUpdateReceiver() {
        return mUpdateReceiver;
    }

    public int getState() {
        return mPlaybackState;
    }

    public long getPosition() {
        return mContentPosition;
    }

    public long getDuration() {
        return mContentDuration;
    }

    public long getTimestamp() {
        return mTimestamp;
    }

    public MediaItemStatus getStatus() {
        return new MediaItemStatus.Builder(mPlaybackState)
            .setContentPosition(mContentPosition)
            .setContentDuration(mContentDuration)
            .setTimestamp(mTimestamp)
            .build();
    }

    @Override
    public String toString() {
        String state[] = {
            "PENDING",
            "PLAYING",
            "PAUSED",
            "BUFFERING",
            "FINISHED",
            "CANCELED",
            "INVALIDATED",
            "ERROR"
        };
        return "[" + mSessionId + "|" + mItemId + "|"
            + (mRemoteItemId != null ? mRemoteItemId : "-") + "|"
            + state[mPlaybackState] + "] " + mTitle + ": " + mUri.toString();
    }
}
