package com.haoyu.app.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.widget.Button
import android.widget.RadioGroup
import com.haoyu.app.base.BaseActivity
import com.haoyu.app.fragment.*
import com.haoyu.app.gdei.student.R
import com.haoyu.app.view.AppToolBar
import java.util.*

/**
 * 创建日期：2018/1/12.
 * 描述:课程学习首页
 * 作者:xiaoma
 */
class CourseTabActivity : BaseActivity() {
    private lateinit var context: CourseTabActivity
    private lateinit var radioGroup: RadioGroup
    private val PAGE_COURSE = 0  //学习
    private val PAGE_RESOURCES = 1    //资源
    private val PAGE_DISCUSSION = 2   //讨论
    private val PAGE_QUESTION = 3 //问答
    private val PAGE_PROGRESS = 4 //进度
    private val fragments = ArrayList<Fragment>()
    private var training: Boolean = false
    private var courseId: String? = null
    private var courseType: String? = null
    private var f1: PageCourseFragment? = null
    private var f2: PageResourcesFragment? = null
    private var f3: PageDiscussionFragment? = null
    private var f4: PageQuestionFragment? = null
    private var f5: PageProgressFragment? = null

    override fun setLayoutResID(): Int {
        return R.layout.activity_course_tab
    }

    override fun initView() {
        context = this
        training = intent.getBooleanExtra("training", false)
        courseId = intent.getStringExtra("courseId")// 课程Id，通过intent获取
        courseType = intent.getStringExtra("courseType")  //课程类型（微课）
        val courseTitle = intent.getStringExtra("courseTitle")
        val toolBar = findViewById<AppToolBar>(R.id.toolBar)
        radioGroup = findViewById(R.id.radioGroup)
        val btDownload = findViewById<Button>(R.id.bt_download)
        toolBar.setTitle_text(courseTitle)
        toolBar.setOnLeftClickListener { finish() }
        setTab(PAGE_COURSE)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_section -> {
                    setTab(PAGE_COURSE)
                }
                R.id.rb_resources -> {
                    setTab(PAGE_RESOURCES)
                }
                R.id.rb_discuss -> {
                    setTab(PAGE_DISCUSSION)
                }
                R.id.rb_wenda -> {
                    setTab(PAGE_QUESTION)
                }
                R.id.rb_progress -> {
                    setTab(PAGE_PROGRESS)
                }
            }
        }
        btDownload.setOnClickListener({
            val intent = Intent()
            intent.setClass(context, AppDownloadActivity::class.java)
            startActivity(intent)
        })
    }

    private fun setTab(index: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (index) {
            PAGE_COURSE -> {
                if (f1 == null) {
                    f1 = PageCourseFragment()
                    f1?.let {
                        val bundle = Bundle()
                        bundle.putBoolean("training", training)
                        bundle.putString("entityId", courseId)
                        bundle.putString("courseType", courseType)
                        it.arguments = bundle
                        transaction.add(R.id.content, it)
                        fragments.add(it)
                    }
                } else {
                    transaction.show(f1)
                }
            }
            PAGE_RESOURCES -> {
                if (f2 == null) {
                    f2 = PageResourcesFragment()
                    f2?.let {
                        val bundle = Bundle()
                        bundle.putString("entityId", courseId)
                        it.arguments = bundle
                        transaction.add(R.id.content, it)
                        fragments.add(it)
                    }
                } else {
                    transaction.show(f2)
                }
            }
            PAGE_DISCUSSION -> {
                if (f3 == null) {
                    f3 = PageDiscussionFragment()
                    f3?.let {
                        val bundle = Bundle()
                        bundle.putString("entityId", courseId)
                        it.arguments = bundle
                        transaction.add(R.id.content, it)
                        fragments.add(it)
                    }
                } else {
                    transaction.show(f3)
                }
            }
            PAGE_QUESTION -> {
                if (f4 == null) {
                    f4 = PageQuestionFragment()
                    f4?.let {
                        val bundle = Bundle()
                        bundle.putString("entityId", courseId)
                        it.arguments = bundle
                        transaction.add(R.id.content, it)
                        fragments.add(it)
                    }
                } else {
                    transaction.show(f4)
                }
            }
            PAGE_PROGRESS -> {
                if (f5 == null) {
                    f5 = PageProgressFragment()
                    f5?.let {
                        it.setOnSelectCallBack(object : PageProgressFragment.OnSelectCallBack {
                            override fun onClickCallBack() {
                                radioGroup.check(R.id.rb_section)
                                setTab(PAGE_COURSE)
                            }
                        })
                        val bundle = Bundle()
                        bundle.putBoolean("training", training)
                        bundle.putString("entityId", courseId)
                        bundle.putString("courseType", courseType)
                        it.arguments = bundle
                        transaction.add(R.id.content, it)
                        fragments.add(it)
                    }
                } else {
                    transaction.show(f5)
                }
            }
        }
        transaction.commit()
    }

    private fun hideFragments(transaction: FragmentTransaction) {
        for (fragment in fragments) {
            transaction.hide(fragment)
        }
    }
}