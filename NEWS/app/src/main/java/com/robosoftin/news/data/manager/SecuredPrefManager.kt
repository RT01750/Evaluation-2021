/*
 * Copyright (c) 2020.
 * Written for McDonalds from Robosoft Technologies Pvt Ltd
 *
 * @Author Mithun M
 */

package com.il.data.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

class SecureSharedPreference(application : Application) : SharedPreferences {


    private val mSharedPreferences : SharedPreferences = application.getSharedPreferences("app_pref", Context.MODE_PRIVATE)

    inner class Editor : SharedPreferences.Editor {
        private var mEditor : SharedPreferences.Editor = mSharedPreferences.edit()

        override fun putBoolean(key : String, value : Boolean) : Editor {
            mEditor.putString(key, encrypt(java.lang.Boolean.toString(value)))
            mEditor.commit()
            return this
        }

        override fun putFloat(key : String, value : Float) : Editor {
            mEditor.putString(key, encrypt(value.toString()))
            mEditor.commit()
            return this
        }

        fun putDouble(key : String?, value : Double?) : Editor {
            mEditor.putString(key, encrypt((value!!).toString()))
            mEditor.commit()
            return this
        }

        override fun putInt(key : String, value : Int) : Editor {
            mEditor.putString(key, encrypt(Integer.toString(value)))
            mEditor.commit()
            return this
        }

        override fun putLong(key : String, value : Long) : Editor {
            mEditor.putString(key, encrypt(value.toString()))
            mEditor.commit()
            return this
        }

        override fun putString(key : String, value : String?) : Editor {
            mEditor.putString(key, encrypt(value))
            mEditor.commit()
            return this
        }

        override fun apply() {
            mEditor.apply()
        }

        override fun clear() : Editor {
            mEditor.clear()
            return this
        }

        override fun commit() : Boolean {
            return mEditor.commit()
        }

        override fun remove(key : String) : Editor {
            mEditor.remove(key)
            mEditor.commit()
            return this
        }

        override fun putStringSet(
                arg0 : String, arg1 : Set<String>?) : SharedPreferences.Editor? {
            return null
        }

    }

    override fun edit() : Editor {
        return Editor()
    }

    override fun getAll() : Map<String, *> {
        throw UnsupportedOperationException()
    }

    override fun getBoolean(key : String, defValue : Boolean) : Boolean {
        val value = mSharedPreferences.getString(key, null)
        return if (value != null) {
            java.lang.Boolean.parseBoolean(decrypt(value))
        } else {
            defValue
        }
    }

    override fun getFloat(key : String, defValue : Float) : Float {
        val value = mSharedPreferences.getString(key, null)
        return if (value != null) {
            decrypt(value).toFloat()
        } else {
            defValue
        }
    }

    fun getDouble(key : String?, defValue : Double?) : Double {
        val value = mSharedPreferences.getString(key, null)
        return (if (value != null) {
            decrypt(value).toDouble()
        } else {
            defValue
        })!!
    }

    override fun getInt(key : String, defValue : Int) : Int {
        val value = mSharedPreferences.getString(key, null)
        return if (value != null) {
            decrypt(value).toInt()
        } else defValue
    }

    override fun getLong(key : String, defValue : Long) : Long {
        val value = mSharedPreferences.getString(key, null)
        return if (value != null) {
            decrypt(value).toLong()
        } else defValue
    }

    override fun getString(key : String, defValue : String?) : String? {
        val value = mSharedPreferences.getString(key, null)
        return value?.let { decrypt(it) } ?: defValue
    }

    override fun contains(key : String) : Boolean {
        return mSharedPreferences.contains(key)
    }

    override fun registerOnSharedPreferenceChangeListener(
            onSharedPreferenceChangeListener : OnSharedPreferenceChangeListener) {
        mSharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener)
    }

    override fun unregisterOnSharedPreferenceChangeListener(
            onSharedPreferenceChangeListener : OnSharedPreferenceChangeListener) {
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener)
    }

    private fun encrypt(toEncrypt : String?) : String {
        return try {
            //Create your Secret Key Spec, which defines the key transformations
            val skeySpec = SecretKeySpec(SECRET_KEY, "AES")

            //Get the cipher text
            val cipher = Cipher.getInstance("AES")

            //Initialize the cipher
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec)

            //Encrypt the string into bytes
            val encryptedBytes = cipher.doFinal(toEncrypt!!.toByteArray())

            //Convert the encrypted bytes back into a string and return
            Base64.encodeToString(encryptedBytes, Base64.URL_SAFE)
        } catch (e : Exception) {
            throw RuntimeException(e)
        }
    }

    private fun decrypt(encryptedText : String) : String {
        return try {
            val skeySpec = SecretKeySpec(SECRET_KEY, "AES")
            val cipher = Cipher.getInstance("AES")
            cipher.init(Cipher.DECRYPT_MODE, skeySpec)
            val toDecrypt = Base64.decode(encryptedText, Base64.URL_SAFE)
            String(cipher.doFinal(toDecrypt))
        } catch (e : Exception) {
            throw RuntimeException(e)
        }
    }

    override fun getStringSet(arg0 : String, arg1 : Set<String>?) : Set<String>? {
        return null
    }

    companion object {
        private val SECRET_KEY = byteArrayOf('y'.toByte(), 'e'.toByte(), 's'.toByte(), 'r'.toByte(), 'o'.toByte(), 'b'.toByte(), 'o'.toByte(),
                's'.toByte(), 'e'.toByte(), 'c'.toByte(), 'r'.toByte(), 'e'.toByte(), 't'.toByte(), 'k'.toByte(), 'e'.toByte(), 'y'.toByte())
    }

}