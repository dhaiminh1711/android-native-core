package com.working.core.utils

import android.content.SharedPreferences

inline fun <reified T> SharedPreferences.get(key: String, defaultValue: T) = with(this) {
	when(T::class) {
		Boolean::class -> getBoolean(key, defaultValue as Boolean) as T
		Float::class -> getFloat(key, defaultValue as Float) as T
		Int::class -> getInt(key, defaultValue as Int) as T
		Long::class -> getLong(key, defaultValue as Long) as T
		String::class -> getString(key, defaultValue as String) as T
		else -> null
	}
}

inline fun <reified T> SharedPreferences.set(key: String, value: T) = with(this.edit()) {
	when(T::class) {
		Boolean::class -> putBoolean(key, value as Boolean)
		Float::class -> putFloat(key, value as Float)
		Int::class -> putInt(key, value as Int)
		Long::class -> putLong(key, value as Long)
		String::class -> putString(key, value as String)
	}
	commit()
}
