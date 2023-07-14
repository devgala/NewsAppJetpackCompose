package com.example.newsappjetpackcompose.pagination

import android.util.Log


class DefaultPaginator<Key, Item>(
    private val initialKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key, category: String) -> Result<List<Item>?>,
    private inline val getNextKey: suspend () -> Key,
    private inline val onError: (Throwable?) -> Unit,
    private inline val onSuccess: (items: List<Item>?, newKey: Key) -> Unit
):Paginator<Item>{
    private var currentKey:Key = initialKey
    private var isMakingRequest:Boolean = false
    override suspend fun loadNextArticles(category: String) {
        if(isMakingRequest) return
        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentKey, category)
        val items = result.getOrElse {
            onError(it)
            isMakingRequest = false
            onLoadUpdated(false)
            return
        }
        currentKey = getNextKey()
        Log.d("scroll","$currentKey " + items.toString() )
        onSuccess(items,currentKey)
        onLoadUpdated(false)
        isMakingRequest =false

    }

    override fun reset() {
        currentKey = initialKey
    }


}