package com.silvertak.relationshipsmanager.vo

import com.silvertak.relationshipsmanager.library.StringLib
import io.reactivex.rxjava3.core.Observable
import java.util.*

class PersonRelationshipArray(arrayList: ArrayList<PersonRelationshipInfo?>) : ArrayList<PersonRelationshipInfo?>() {

    init {
        Observable.fromIterable(arrayList).subscribe{add(it)}
    }

    /**
     * 가장 많이 연락한 5명을 ArrayList 형태로 리턴한다.
     * @return
     */
    val mostContactRanking: PersonRelationshipArray
        get() {
            Collections.sort(this)
            return this
            /*val resultArray = ArrayList<PersonRelationshipInfo>()
            for (info in this) {
                resultArray.add(info)
                //if(resultArray.size() == 5) break;
            }
            return resultArray*/
        }

    fun getInfoById(id: String): PersonRelationshipInfo? {
        for (info in this) {
            if (info!!.contactInfo.id.isNullOrEmpty() && info.contactInfo.id == id) return info

        }
        return null
    }
}