package com.andreasgift.moviesapp.data.db

import androidx.room.TypeConverter
import com.andreasgift.moviesapp.data.model.*
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken

class MovieTypeConverter {
    @TypeConverter
    fun belongsToCollectionToJson(value: BelongsToCollection?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToBelongsToCollection(value: String): BelongsToCollection =
        Gson().fromJson(value, BelongsToCollection::class.java)

    @TypeConverter
    fun companyToJson(value: ProductionCompanies?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToCompany(value: String): ProductionCompanies =
        Gson().fromJson(value, ProductionCompanies::class.java)

    @TypeConverter
    fun countryToJson(value: ProductionCountries?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToCountry(value: String): ProductionCountries =
        Gson().fromJson(value, ProductionCountries::class.java)

    @TypeConverter
    fun languageToJson(value: SpokenLanguages?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToLanguage(value: String): SpokenLanguages =
        Gson().fromJson(value, SpokenLanguages::class.java)

    @TypeConverter
    fun genreListToJson(value: ArrayList<Genres>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToListGenre(value: String): ArrayList<Genres> =
        Gson().fromJson<ArrayList<Genres>>(value, object : TypeToken<ArrayList<Genres>>() {}.type)

    @TypeConverter
    fun producCompaniesToJson(value: ArrayList<ProductionCompanies>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToProdCompanies(value: String): ArrayList<ProductionCompanies> =
        Gson().fromJson<ArrayList<ProductionCompanies>>(value, object : TypeToken<ArrayList<ProductionCompanies>>() {}.type)

    @TypeConverter
    fun producCountriesToJson(value: ArrayList<ProductionCountries>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToProdCountries(value: String): ArrayList<ProductionCountries> =
        Gson().fromJson<ArrayList<ProductionCountries>>(value, object : TypeToken<ArrayList<ProductionCountries>>() {}.type)

    @TypeConverter
    fun languageToJson(value: ArrayList<SpokenLanguages>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToLanguages(value: String): ArrayList<SpokenLanguages> =
        Gson().fromJson<ArrayList<SpokenLanguages>>(value, object : TypeToken<ArrayList<SpokenLanguages>>() {}.type)
}