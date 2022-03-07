package gorosheg.episodes.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import gorosheg.episodes.R
import gorosheg.episodes.presentation.recycler.EpisodesAdapter
import gorosheg.myapplication.R.*
import gorosheg.myapplication.navigator.DescriptionNavigator
import gorosheg.myapplication.navigator.EpisodesNavigator
import gorosheg.myapplication.utils.showToast
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class EpisodesFragment : Fragment(R.layout.fragment_episodes) {

    private val rootView by lazy { requireNotNull(view) }
    private val recyclerView: RecyclerView by lazy { rootView.findViewById(R.id.EpisodesList) }
    private val adapter: EpisodesAdapter by lazy { EpisodesAdapter() }
    private val navigator: EpisodesNavigator by inject()
    private val disposable = CompositeDisposable()

    private val characterId: Int by lazy {
        arguments?.getInt(CHARACTERS_ID) as Int
    }
    private val viewModel: EpisodesViewModel by viewModel { parametersOf(characterId) }
    private val swipeRefresh: SwipeRefreshLayout by lazy { rootView.findViewById(R.id.charactersRefresh) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            navigator.back(requireActivity())
        }
        recyclerView.adapter = adapter
        swipeRefresh.setOnRefreshListener { getEpisodes() }
        getEpisodes()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    private fun getEpisodes() {

        disposable += viewModel.getEpisodes()
            .subscribe { character -> adapter.items = character }

        disposable += viewModel.error
            .subscribe {
                it.printStackTrace()
                showToast(getString(string.unknown_error))
            }
    }

    companion object {

        private const val CHARACTERS_ID = "CHARACTERS_ID"

        fun newInstance(characterId: Int) = EpisodesFragment().apply {
            arguments = Bundle().apply {
                putInt(CHARACTERS_ID, characterId)
            }
        }
    }

}