package com.silvertak.relationshipsmanager.customInterface;

import com.silvertak.relationshipsmanager.data.RelationshipGroupInfo;

public interface ManagingGroupListener {
    void onInfoClick(RelationshipGroupInfo info);
    void onDeleteClick(RelationshipGroupInfo info);
    void onModifyClick(RelationshipGroupInfo info);
}
