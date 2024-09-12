package com.app.incroyable.videocall_prank.core.storage

import android.net.Uri

object MyStaticVariable {
    private var _imgUri: Uri? = null
    private var _vidUri: Uri? = null

    var imgUri: Uri?
        get() = _imgUri
        set(value) {
            _imgUri = value
        }

    var vidUri: Uri?
        get() = _vidUri
        set(value) {
            _vidUri = value
        }
}