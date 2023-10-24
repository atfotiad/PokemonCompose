package com.atfotiad.pokemoncompose.ui

data class PagingIndex(val limit :Int ,val offset:Int, val total :Int) {

    fun pagingIndexing(offset: Int,totalFromServer: Int,totalFromPaging:Int):Pair<PagingIndex?, PagingIndex?>{
        var nextKeyOffset : Int? = offset + loadSize
        if (offset != 0 && offset >= totalFromPaging){
            nextKeyOffset = null
        }
        var previousKeyOffset : Int? = offset
        if (offset == 0){
            previousKeyOffset = null
        }
        val previousKey = previousKeyOffset?.let {
            PagingIndex(limit= loadSize,offset = it ,total = totalFromServer)
        }
        val nextKey = nextKeyOffset?.let {
            PagingIndex(limit= loadSize,offset = it,total = totalFromServer)
        }
        return Pair(previousKey, nextKey)

    }
}
