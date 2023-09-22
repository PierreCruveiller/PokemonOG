package com.og.pokemonog.databinding;
import com.og.pokemonog.R;
import com.og.pokemonog.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class PokemonInfoItemBindingImpl extends PokemonInfoItemBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.separator, 9);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public PokemonInfoItemBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }
    private PokemonInfoItemBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.TextView) bindings[3]
            , (android.widget.ImageView) bindings[1]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[5]
            );
        this.desc.setTag(null);
        this.front.setTag(null);
        this.height.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.name.setTag(null);
        this.number.setTag(null);
        this.type1Text.setTag(null);
        this.type2Text.setTag(null);
        this.weight.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x200L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.pokemonViewModel == variableId) {
            setPokemonViewModel((com.og.pokemonog.PokemonViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setPokemonViewModel(@Nullable com.og.pokemonog.PokemonViewModel PokemonViewModel) {
        updateRegistration(0, PokemonViewModel);
        this.mPokemonViewModel = PokemonViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.pokemonViewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangePokemonViewModel((com.og.pokemonog.PokemonViewModel) object, fieldId);
        }
        return false;
    }
    private boolean onChangePokemonViewModel(com.og.pokemonog.PokemonViewModel PokemonViewModel, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        else if (fieldId == BR.front) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        else if (fieldId == BR.name) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        else if (fieldId == BR.desc) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        else if (fieldId == BR.height) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        else if (fieldId == BR.weight) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
            }
            return true;
        }
        else if (fieldId == BR.type1) {
            synchronized(this) {
                    mDirtyFlags |= 0x40L;
            }
            return true;
        }
        else if (fieldId == BR.type2) {
            synchronized(this) {
                    mDirtyFlags |= 0x80L;
            }
            return true;
        }
        else if (fieldId == BR.number) {
            synchronized(this) {
                    mDirtyFlags |= 0x100L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        android.graphics.drawable.Drawable pokemonViewModelGetImageContextPokemonViewModelFront = null;
        java.lang.String pokemonViewModelType1 = null;
        java.lang.String pokemonViewModelName = null;
        java.lang.String pokemonViewModelType2 = null;
        int pokemonViewModelFront = 0;
        com.og.pokemonog.PokemonViewModel pokemonViewModel = mPokemonViewModel;
        java.lang.String pokemonViewModelWeight = null;
        java.lang.String pokemonViewModelHeight = null;
        java.lang.String pokemonViewModelDesc = null;
        java.lang.String pokemonViewModelNumber = null;

        if ((dirtyFlags & 0x3ffL) != 0) {


            if ((dirtyFlags & 0x241L) != 0) {

                    if (pokemonViewModel != null) {
                        // read pokemonViewModel.type1
                        pokemonViewModelType1 = pokemonViewModel.getType1();
                    }
            }
            if ((dirtyFlags & 0x205L) != 0) {

                    if (pokemonViewModel != null) {
                        // read pokemonViewModel.name
                        pokemonViewModelName = pokemonViewModel.getName();
                    }
            }
            if ((dirtyFlags & 0x281L) != 0) {

                    if (pokemonViewModel != null) {
                        // read pokemonViewModel.type2
                        pokemonViewModelType2 = pokemonViewModel.getType2();
                    }
            }
            if ((dirtyFlags & 0x203L) != 0) {

                    if (pokemonViewModel != null) {
                        // read pokemonViewModel.front
                        pokemonViewModelFront = pokemonViewModel.getFront();
                    }


                    if (pokemonViewModel != null) {
                        // read pokemonViewModel.getImage(context, pokemonViewModel.front)
                        pokemonViewModelGetImageContextPokemonViewModelFront = pokemonViewModel.getImage(getRoot().getContext(), pokemonViewModelFront);
                    }
            }
            if ((dirtyFlags & 0x221L) != 0) {

                    if (pokemonViewModel != null) {
                        // read pokemonViewModel.weight
                        pokemonViewModelWeight = pokemonViewModel.getWeight();
                    }
            }
            if ((dirtyFlags & 0x211L) != 0) {

                    if (pokemonViewModel != null) {
                        // read pokemonViewModel.height
                        pokemonViewModelHeight = pokemonViewModel.getHeight();
                    }
            }
            if ((dirtyFlags & 0x209L) != 0) {

                    if (pokemonViewModel != null) {
                        // read pokemonViewModel.desc
                        pokemonViewModelDesc = pokemonViewModel.getDesc();
                    }
            }
            if ((dirtyFlags & 0x301L) != 0) {

                    if (pokemonViewModel != null) {
                        // read pokemonViewModel.number
                        pokemonViewModelNumber = pokemonViewModel.getNumber();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x209L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.desc, pokemonViewModelDesc);
        }
        if ((dirtyFlags & 0x203L) != 0) {
            // api target 1

            androidx.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(this.front, pokemonViewModelGetImageContextPokemonViewModelFront);
        }
        if ((dirtyFlags & 0x211L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.height, pokemonViewModelHeight);
        }
        if ((dirtyFlags & 0x205L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.name, pokemonViewModelName);
        }
        if ((dirtyFlags & 0x301L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.number, pokemonViewModelNumber);
        }
        if ((dirtyFlags & 0x241L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.type1Text, pokemonViewModelType1);
        }
        if ((dirtyFlags & 0x281L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.type2Text, pokemonViewModelType2);
        }
        if ((dirtyFlags & 0x221L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.weight, pokemonViewModelWeight);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): pokemonViewModel
        flag 1 (0x2L): pokemonViewModel.front
        flag 2 (0x3L): pokemonViewModel.name
        flag 3 (0x4L): pokemonViewModel.desc
        flag 4 (0x5L): pokemonViewModel.height
        flag 5 (0x6L): pokemonViewModel.weight
        flag 6 (0x7L): pokemonViewModel.type1
        flag 7 (0x8L): pokemonViewModel.type2
        flag 8 (0x9L): pokemonViewModel.number
        flag 9 (0xaL): null
    flag mapping end*/
    //end
}