package com.padcmyanmar.mewz.themovieapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.padcmyanmar.mewz.themovieapp.R
import com.padcmyanmar.mewz.themovieapp.adapters.BannerAdapter
import com.padcmyanmar.mewz.themovieapp.adapters.ShowCaseAdapter
import com.padcmyanmar.mewz.themovieapp.data.models.MovieModel
import com.padcmyanmar.mewz.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.mewz.themovieapp.data.vos.GenreVO
import com.padcmyanmar.mewz.themovieapp.databinding.ActivityMainBinding
import com.padcmyanmar.mewz.themovieapp.delegates.BannerViewHolderDelegate
import com.padcmyanmar.mewz.themovieapp.delegates.MovieViewHolderDelegate
import com.padcmyanmar.mewz.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.padcmyanmar.mewz.themovieapp.mvvm.MainViewModel
import com.padcmyanmar.mewz.themovieapp.utils.dummyGenreList
import com.padcmyanmar.mewz.themovieapp.viewpods.ActorListViewPod
import com.padcmyanmar.mewz.themovieapp.viewpods.MovieListViewPod

class MainActivity : AppCompatActivity(), BannerViewHolderDelegate, ShowcaseViewHolderDelegate, MovieViewHolderDelegate{

    private lateinit var binding: ActivityMainBinding

    private lateinit var mBannerAdapter: BannerAdapter
    private lateinit var mShowcaseAdapter: ShowCaseAdapter

    private lateinit var mBestPopularMoviesListViewPod: MovieListViewPod
    private lateinit var mMoviesByGenreViewPod: MovieListViewPod

    private lateinit var mActorListViewPod: ActorListViewPod

    // ViewModel
    private lateinit var mMainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewModel()

        setUpToolbar()
        setUpViewPod()
        setUpBannerViewPager()
        setUpShowCaseRecyclerView()

        setUpListeners()

        // Observe Live Data
        observeLiveData()

    }


    private fun setUpViewModel() {
        mMainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mMainViewModel.getInitialData()
    }

    private fun observeLiveData() {
        mMainViewModel.nowPlayingMoviesLiveData?.observe(this, mBannerAdapter::setNewData)
        mMainViewModel.popularMoviesLiveData?.observe(this, mBestPopularMoviesListViewPod::setData)
        mMainViewModel.topRatedMoviesLiveData?.observe(this, mShowcaseAdapter::setNewData)
        mMainViewModel.genreLiveData.observe(this, this::setUpGenreTabLayout)
        mMainViewModel.moviesByGenreLiveData.observe(this, mMoviesByGenreViewPod::setData)
        mMainViewModel.actorLiveData.observe(this, mActorListViewPod::setData)
    }


    private fun setUpViewPod() {
        mBestPopularMoviesListViewPod = binding.vpBestPopularMoviesList.root
        mBestPopularMoviesListViewPod.setUpMovieListViewPod(this)

        mMoviesByGenreViewPod = binding.vpMoviesByGenre.root
        mMoviesByGenreViewPod.setUpMovieListViewPod(this)

        mActorListViewPod = binding.vpActorList.root
//        mActorListViewPod.setUpActorViewPod("","","")
    }


    private fun setUpListeners() {
        binding.tabLayoutGenre.addOnTabSelectedListener(object: OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                mMainViewModel.getMoviesByGenre(tab?.position ?: 0)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })
    }

    private fun setUpShowCaseRecyclerView() {
        mShowcaseAdapter = ShowCaseAdapter(this)
        binding.rvShowcases.adapter = mShowcaseAdapter
        binding.rvShowcases.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpGenreTabLayout(genreList: List<GenreVO>) {
        genreList.forEach {
            binding.tabLayoutGenre.newTab().apply {
                text = it.name
                binding.tabLayoutGenre.addTab(this)
            }
        }
    }


    private fun setUpBannerViewPager() {
        mBannerAdapter = BannerAdapter(this)
        binding.viewPagerBanner.adapter = mBannerAdapter

        binding.dotsIndicatorBanner.attachTo(binding.viewPagerBanner)
    }

    private fun setUpToolbar() {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_discover, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.searchMovie-> {
                startActivity(SearchActivity.newIntent(this))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onTapMovieFromBanner(movieId: Int) {
        startActivity(MovieDetailActivity.newIntent(this, movieId))
    }

    override fun onTapMovieFromShowcase(movieId: Int) {
        startActivity(MovieDetailActivity.newIntent(this, movieId))
    }

    override fun onTapMovie(movieId: Int) {
        startActivity(MovieDetailActivity.newIntent(this, movieId))
    }

    private fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }
}