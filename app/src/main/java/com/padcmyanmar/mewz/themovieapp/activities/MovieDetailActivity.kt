package com.padcmyanmar.mewz.themovieapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.padcmyanmar.mewz.themovieapp.R
import com.padcmyanmar.mewz.themovieapp.data.models.MovieModel
import com.padcmyanmar.mewz.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.mewz.themovieapp.data.vos.GenreVO
import com.padcmyanmar.mewz.themovieapp.data.vos.MovieVO
import com.padcmyanmar.mewz.themovieapp.databinding.ActivityMovieDetailBinding
import com.padcmyanmar.mewz.themovieapp.mvvm.MovieDetailViewModel
import com.padcmyanmar.mewz.themovieapp.utils.IMAGE_BASE_URL
import com.padcmyanmar.mewz.themovieapp.viewpods.ActorListViewPod

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    private lateinit var mActorViewPod: ActorListViewPod
    private lateinit var mCreatorViewPod: ActorListViewPod

    // ViewModel
    private lateinit var mViewModel: MovieDetailViewModel

    companion object{

        const val IE_MOVIE_ID = "IE_MOVIE_ID"

        fun newIntent(context: Context, movieId: Int): Intent{
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(IE_MOVIE_ID, movieId)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieId = intent?.getIntExtra(IE_MOVIE_ID, 0)
        movieId?.let {
            setUpViewModel(it)
        }

        setUpViewPods()

        setUpListeners()

        observeLiveData()

    }


    private fun setUpViewModel(movieId: Int) {
        mViewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]
        mViewModel.getInitialData(movieId)
    }

    private fun observeLiveData() {
        mViewModel.movieDetailLiveData?.observe(this){
            it?.let { movie -> bindData(movie) }
        }
        mViewModel.castLiveData.observe(this, mActorViewPod::setData)
        mViewModel.crewLiveData.observe(this, mCreatorViewPod::setData)
    }

    private fun bindData(movie: MovieVO) {

        Glide.with(this)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(binding.ivMovieImage)

        binding.collapsingTitle.title = movie.originalTitle ?: ""
        binding.tvMovieName.text = movie.originalTitle ?: ""
        binding.tvMovieReleaseYear.text = movie.releaseDate?.substring(0,4)
        binding.tvRating.text = movie.voteAverage?.toString() ?: ""
        binding.rbMovieRating.rating = movie.getRatingBaseOnFiveStars()

        movie.voteCount?.let { binding.tvNumberOfVotes.text = "$it VOTES" }

        bindGenres(movie, movie.genres ?: listOf())

        binding.tvOverview.text = movie.overview ?: ""
        binding.tvOriginalTitle.text = movie.originalTitle ?: ""
        binding.tvType.text = movie.getGenresAsCommaSeparatedString()
        binding.tvProduction.text = movie.getProductionCountriesAsCommaSeparatedString()
        binding.tvPremiere.text = movie.tagline ?: ""
        binding.tvDescription.text = movie.overview ?: ""


    }

    private fun bindGenres(movie: MovieVO, genre: List<GenreVO>) {
        movie.genres?.count()?.let {
            binding.tvFirstGenre.text = genre.firstOrNull()?.name ?: ""
            binding.tvSecondGenre.text = genre.getOrNull(1)?.name ?: ""
            binding.tvThirdGenre.text = genre.getOrNull(2)?.name ?: ""

            if (it<3){
                binding.tvThirdGenre.visibility = View.GONE
            }else if (it<2){
                binding.tvSecondGenre.visibility = View.GONE
            }
        }

    }

    private fun setUpListeners() {
        binding.btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setUpViewPods() {
        mActorViewPod = binding.vpActorList.root
        mActorViewPod.setUpActorViewPod(
            backgroundColorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_actors),
            moreTitleText = ""
        )

        mCreatorViewPod = binding.vpCreatorList.root
        mCreatorViewPod.setUpActorViewPod(
            backgroundColorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_creators),
            moreTitleText = getString(R.string.lbl_more_creators)
        )
    }

    private fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }
}