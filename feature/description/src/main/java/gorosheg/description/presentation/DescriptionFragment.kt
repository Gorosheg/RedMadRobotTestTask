package gorosheg.description.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import gorosheg.description.R
import gorosheg.myapplication.R.*
import gorosheg.myapplication.model.Description
import gorosheg.myapplication.model.Location
import gorosheg.myapplication.navigator.DescriptionNavigator
import gorosheg.myapplication.utils.showToast
import gorosheg.myapplication.utils.toolbarSettings
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DescriptionFragment : Fragment(R.layout.fragment_description) {

    private val rootView by lazy { requireNotNull(view) }
    private val viewModel: DescriptionViewModel by viewModel { parametersOf(characterId) }

    private val disposable = CompositeDisposable()
    private val navigator: DescriptionNavigator by inject()

    private val openEpisodes: Button by lazy { rootView.findViewById(R.id.openEpisodes) }

    private val characterId: Int by lazy {
        arguments?.getInt(CHARACTER_KEY) as Int
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)

        toolbar.toolbarSettings(
            activity = activity as AppCompatActivity,
            isBackArrowEnabled = true,
            callback = navigator::back
        )

        subscribeOnViewModel()
        getData()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    private fun subscribeOnViewModel() {
        disposable += viewModel.description
            .subscribe(::handleDescription)

        disposable += viewModel.location
            .subscribe(::handleLocation)

        disposable += viewModel.error
            .subscribe {
                it.printStackTrace()
                showToast(getString(string.unknown_error))
            }
    }

    private fun getData() {
        viewModel.getDescription()
        viewModel.getLocation()
    }

    @SuppressLint("SetTextI18n")
    private fun handleDescription(description: Description) {
        val photo: ImageView = rootView.findViewById(R.id.photo)
        val name: TextView = rootView.findViewById(R.id.name)
        val species: TextView = rootView.findViewById(R.id.species)
        val status: TextView = rootView.findViewById(R.id.status)

        openEpisodes.setOnClickListener {
            navigateToEpisodesScreen(characterId)
        }

        Glide.with(rootView)
            .load(description.image)
            .into(photo)

        photo.clipToOutline = true
        name.text = getString(string.char_name, description.name)
        species.text = getString(string.char_species, description.species)
        status.text = getString(string.char_status, description.status)
    }

    @SuppressLint("SetTextI18n")
    private fun handleLocation(location: Location) {
        val locationView: TextView = rootView.findViewById(R.id.location)
        locationView.text = getString(string.char_location, location.name, location.dimension)
    }

    private fun navigateToEpisodesScreen(characterId: Int) {
        navigator.navigateToEpisodesScreen(requireActivity(), characterId)
    }

    companion object {

        private const val CHARACTER_KEY = "CHARACTER_KEY"

        fun newInstance(characterId: Int) = DescriptionFragment().apply {
            arguments = Bundle().apply {
                putInt(CHARACTER_KEY, characterId)
            }
        }
    }
}