package com.trey.workstation

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.view.ViewGroup
import android.view.WindowManager

class MainActivity : Activity() {

    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
        )

        webView = WebView(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.allowFileAccess = true
            settings.allowContentAccess = true

            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
        }

        setContentView(webView)

        webView.loadDataWithBaseURL(
            "https://workstation.local/",
            WORKSPACE_HTML,
            "text/html",
            "UTF-8",
            null
        )
    }

    override fun onDestroy() {
        webView.destroy()
        super.onDestroy()
    }

    companion object {
        private const val WORKSPACE_HTML = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport"
                      content="width=device-width,
                               initial-scale=1.0,
                               maximum-scale=1.0,
                               user-scalable=no">
                <title>World Workstation</title>

                <style>
                    * {
                        box-sizing: border-box;
                    }

                    body {
                        margin: 0;
                        min-height: 100vh;
                        background: #080808;
                        color: #f5f5f5;
                        font-family: sans-serif;
                        display: flex;
                        flex-direction: column;
                    }

                    header {
                        padding: 20px;
                        border-bottom: 1px solid #262626;
                    }

                    header h1 {
                        margin: 0;
                        font-size: 20px;
                    }

                    header p {
                        margin: 8px 0 0;
                        color: #999;
                        font-size: 13px;
                    }

                    main {
                        flex: 1;
                        display: flex;
                        align-items: center;
                        justify-content: center;
                        padding: 24px;
                        text-align: center;
                    }

                    .status {
                        max-width: 320px;
                    }

                    .indicator {
                        width: 10px;
                        height: 10px;
                        background: #ff792e;
                        border-radius: 50%;
                        display: inline-block;
                        margin-right: 8px;
                    }

                    h2 {
                        font-size: 22px;
                    }

                    .description {
                        color: #aaa;
                        font-size: 14px;
                        line-height: 1.7;
                    }

                    footer {
                        padding: 16px;
                        text-align: center;
                        color: #666;
                        font-size: 12px;
                        border-top: 1px solid #262626;
                    }
                </style>
            </head>

            <body>
                <header>
                    <h1>World Workstation</h1>
                    <p>Procedural development environment</p>
                </header>

                <main>
                    <section class="status">
                        <span class="indicator"></span>
                        <span>Native Android host active</span>

                        <h2>Ready for your world.</h2>

                        <p class="description">
                            The workstation viewport is running.
                            HTML, CSS, JavaScript, Three.js and
                            native Rust computation will form
                            the project execution environment.
                        </p>
                    </section>
                </main>

                <footer>
                    WORKSTATION CORE · ANDROID
                </footer>
            </body>
            </html>
        """
    }
}
