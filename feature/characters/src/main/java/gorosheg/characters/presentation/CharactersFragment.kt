package gorosheg.characters.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import gorosheg.characters.R
import gorosheg.characters.presentation.recycler.CharacterAdapter
import gorosheg.characters.presentation.recycler.recyclerViewListener
import gorosheg.myapplication.navigator.CharacterNavigator
import gorosheg.myapplication.R.*
import gorosheg.myapplication.model.Character
import gorosheg.myapplication.utils.showToast
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment : Fragment(R.layout.fragment_characters) {

    private val rootView by lazy { requireNotNull(view) }
    private val recyclerView: RecyclerView by lazy { rootView.findViewById(R.id.CharacterList) }
    private val adapter: CharacterAdapter by lazy { CharacterAdapter(::navigateToDescriptionScreen) }
    private val viewModel: CharactersViewModel by viewModel()
    private val swipeRefresh: SwipeRefreshLayout by lazy { rootView.findViewById(R.id.charactersRefresh) }

    private val disposable = CompositeDisposable()
    private val navigator: CharacterNavigator by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.adapter = adapter
        swipeRefresh.setOnRefreshListener { getCharacters(1) }
        getCharacters(1)

        subscribeToViewModel()

        recyclerView.recyclerViewListener(::getCharacters)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    private fun subscribeToViewModel() {
        disposable += viewModel.success
            .subscribe { character ->
                adapter.items = character
            }

        disposable += viewModel.error
            .subscribe {
                it.printStackTrace()
                showToast(getString(string.unknown_error))
            }
    }

    private fun navigateToDescriptionScreen(character: Character) {
        navigator.navigateToDescriptionScreen(requireActivity(), character.id)
    }

    private fun getCharacters(page: Int) {
        loaderIsActive(true)
        viewModel.getCharacters(page)
        loaderIsActive(false)
    }

    private fun loaderIsActive(isActive: Boolean) {
        swipeRefresh.isRefreshing = isActive
    }

    companion object {

        fun newInstance() = CharactersFragment()

    }
}