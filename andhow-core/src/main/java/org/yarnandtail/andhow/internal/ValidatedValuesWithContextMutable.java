package org.yarnandtail.andhow.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.yarnandtail.andhow.api.*;

/**
 * Mutable ValidatedValues implementation w/ context info.
 * 
 * @author eeverman
 */
public class ValidatedValuesWithContextMutable extends ValidatedValuesWithContextBase {
	
	/** List of maps of values that were loaded by each loader */
	private final ArrayList<LoaderValues> loadedValuesList = new ArrayList();
	private boolean problem = false;
	
	public ValidatedValuesWithContextMutable() {
	}
	
	public void addValues(LoaderValues values) {
		loadedValuesList.add(values);
		if (values.getProblems().size() > 0) problem = true;
	}
	
	@Override
	public ValidatedValuesWithContext getValueMapWithContextImmutable() {
		return new ValidatedValuesWithContextImmutable(loadedValuesList);
	}
	
	@Override
	public <T> T getExplicitValue(Property<T> prop) {
		return getExplicitValue(loadedValuesList, prop);
	}
	
	@Override
	public <T> T getValue(Property<T> prop) {
		return getEffectiveValue(loadedValuesList, prop);
	}

	@Override
	public boolean isExplicitlySet(Property<?> prop) {
		return isPropertyPresent(loadedValuesList, prop);
	}
	
	@Override
	public List<LoaderValues> getAllLoaderValues() {
		return Collections.unmodifiableList(loadedValuesList);
	}
	
	@Override
	public LoaderValues getAllValuesLoadedByLoader(Loader loader) {
		return getAllValuesLoadedByLoader(loadedValuesList, loader);
	}

	@Override
	public LoaderValues getEffectiveValuesLoadedByLoader(Loader loader) {
		return getEffectiveValuesLoadedByLoader(loadedValuesList, loader);
	}

	@Override
	public ValidatedValues getValueMapImmutable() {
		return buildValueMapImmutable(loadedValuesList);
	}

	@Override
	public boolean hasProblems() {
		return problem;
	}

}
