package io.xavatarlabs.atlaskeys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.xavatarlabs.atlaskeys.databinding.CoreBinding

class Core : AppCompatActivity() {

    private lateinit var binding: CoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Inflate using View Binding
        binding = CoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val semantic = BuildConfig.VERSION_NAME       // v1.2.56 format
        val semantic = BuildConfig.VERSION_SEMANTIC        // e.g., "v1.2.56"
        //val commitOnly = BuildConfig.VERSION_COMMITCOUNT   // v1234
        val commitOnly = semantic.substringAfterLast('.')  // e.g., "56"
        val dateVer = BuildConfig.VERSION_DATE         // v2026.02.06

        // Assign text to views using binding references
        binding.tvVersionSemantic.text = "App Version: $semantic"
        binding.tvVersionCommit.text = "Commits: $commitOnly"
        binding.tvVersionDate.text = "Build Date: $dateVer"
    }
}