package com.focusings.focusingsworld.dagger.peractivity.components;

import com.focusings.focusingsworld.dagger.PerActivity;
import com.focusings.focusingsworld.dagger.peractivity.modules.ActivityModule;
import com.focusings.focusingsworld.dagger.perapplication.components.ApplicationComponent;
import com.focusings.focusingsworld.mainchannel.MainChannelFragment;

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
