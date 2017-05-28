package com.focusings.focusingsworld.infrastructure.dagger.peractivity.components;

import com.focusings.focusingsworld.infrastructure.dagger.PerActivity;
import com.focusings.focusingsworld.infrastructure.dagger.peractivity.modules.ActivityModule;
import com.focusings.focusingsworld.infrastructure.dagger.perapplication.components.ApplicationComponent;
import com.focusings.focusingsworld.presentation.mainchannel.view.MainChannelFragment;

import dagger.Component;

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 * <p>
 * Subtypes of ActivityComponent should be decorated with annotation: {@link PerActivity}
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainChannelFragment mainChannelFragment);
}
