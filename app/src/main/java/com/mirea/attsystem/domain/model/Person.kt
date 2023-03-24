package com.mirea.attsystem.domain.model

import android.os.Parcel
import android.os.Parcelable
import com.mirea.attsystem.data.db.entities.PersonEntity

data class Person(
    val uid: Long,
    val name: String,
    val lastName: String,
    val jobTitle: String,
    val gender: Char
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt().toChar()
    ) {
    }

    fun toPersonEntity() = PersonEntity(
        uid = uid,
        name = name,
        lastName = lastName,
        jobTitle = jobTitle,
        gender = gender
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(uid)
        parcel.writeString(name)
        parcel.writeString(lastName)
        parcel.writeString(jobTitle)
        parcel.writeInt(gender.toInt())
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Person> {
        override fun createFromParcel(parcel: Parcel): Person {
            return Person(parcel)
        }

        override fun newArray(size: Int): Array<Person?> {
            return arrayOfNulls(size)
        }
    }
}
