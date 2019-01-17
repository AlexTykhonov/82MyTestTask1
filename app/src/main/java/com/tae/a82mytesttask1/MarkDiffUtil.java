// нужно вызывать сервер перед запуском приложения, например через постман
// "https://spring-boot-mysql-server-part0.herokuapp.com/"

package com.tae.a82mytesttask1;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.ArrayList;

public class MarkDiffUtil extends DiffUtil.Callback {

    ArrayList<Mark> oldMarkList;
    ArrayList<Mark> newMarkList;

    public MarkDiffUtil(ArrayList<Mark> oldMarkList, ArrayList<Mark> newMarkList) {
        this.oldMarkList = oldMarkList;
        this.newMarkList = newMarkList;
    }

    @Override
    public int getOldListSize() {
        return oldMarkList.size();
    }

    @Override
    public int getNewListSize() {
        return newMarkList.size();
    }

    @Override
    public boolean areItemsTheSame(int i, int i1) {
        return oldMarkList.get(i).getId()==newMarkList.get(i1).getId();
    }

    @Override
    public boolean areContentsTheSame(int i, int i1) {
        final Mark oldMark = oldMarkList.get(i);
        final Mark newMark = newMarkList.get(i1);
        return oldMark.equals(newMark);
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
