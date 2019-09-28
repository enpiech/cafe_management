package fit.tdc.edu.vn.cafemanagement.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class BaseBindingListAdapter<T> extends ListAdapter<T, BaseBindingListAdapter.BindingViewHolder> {

    private final LayoutInflater mInflater;
    private final @LayoutRes int mResId;

    public BaseBindingListAdapter(Context context, @LayoutRes int resId, DiffUtil.ItemCallback<T> callback) {
        super(callback);
        this.mInflater = LayoutInflater.from(context);
        this.mResId = resId;
    }

    @NonNull
    @Override
    public BaseBindingListAdapter.BindingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(mInflater, mResId, parent, false);
        return new BaseBindingListAdapter.BindingViewHolder(binding);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull BaseBindingListAdapter.BindingViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.onAttach();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull BaseBindingListAdapter.BindingViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.onDetach();
    }

    @Override
    public void onBindViewHolder(@NonNull BaseBindingListAdapter.BindingViewHolder holder, int position) {
        T item = getItem(position);
        holder.bind(item);
    }

    class BindingViewHolder extends RecyclerView.ViewHolder implements LifecycleOwner {

        private final ViewDataBinding mBinding;
        private final LifecycleRegistry mLifecycleRegistry;


        BindingViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
            mLifecycleRegistry = new LifecycleRegistry(this);
            mLifecycleRegistry.setCurrentState(Lifecycle.State.INITIALIZED);
        }

        void onAttach() {
            mLifecycleRegistry.setCurrentState(Lifecycle.State.STARTED);
        }

        void onDetach() {
            mLifecycleRegistry.setCurrentState(Lifecycle.State.DESTROYED);
        }

        /**
         * Bind data to view holder
         * @param item item need binding
         * @param <T> type of item
         */
        <T> void bind(T item) {
//            this.mBinding.setVariable(com.example.cafemanagement.BR.item, item);
//            this.mBinding.getRoot().setOnClickListener(view -> mOnClickListener.onWeatherItemClick(getAdapterPosition()));
            this.mBinding.executePendingBindings();
        }

        @NonNull
        @Override
        public Lifecycle getLifecycle() {
            return mLifecycleRegistry;
        }
    }
}
