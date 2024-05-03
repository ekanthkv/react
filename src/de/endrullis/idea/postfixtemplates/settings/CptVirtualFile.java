package de.endrullis.idea.postfixtemplates.settings;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.net.URL;

/**
 * @author Stefan Endrullis &lt;stefan@endrullis.de&gt;
 */
public class CptVirtualFile {

	/** ID. */
	@Getter
	@Setter
	@Nullable
	private String id;
	/** Local (file) or remote (web) address of the templates file to copy. */
	@Getter
	@Setter
	@Nullable
	private URL  url;
	/** Local file (local copy) in the plugin directory . */
	@Getter
	@Setter
	private File file;
	/** Old URL used when changing the file in the settings dialog. */
	@Getter
	@Setter
	@Nullable
	private URL  oldUrl;
	/** Old file used when changing the the file in the settings dialog. */
	@Getter
	@Setter
	@Nullable
	private File oldFile;
	/** New state used when creating a file in the settings dialog. */
	@Getter
	@Setter
	private boolean isNew;
	/** Web template file info if available. */
	@Getter
	@Setter
	@Nullable
	private WebTemplateFile webTemplateFile;

	public CptVirtualFile(@Nullable String id, @Nullable URL url, @NotNull File file) {
		this.id = id;
		this.url = url;
		this.file = file;
	}

	public CptVirtualFile(@Nullable String id, @Nullable URL url, @NotNull File file, boolean isNew) {
		this.id = id;
		this.url = url;
		this.file = file;
		this.isNew = isNew;
	}

	public String getName() {
		return file.getName();
	}

	public boolean isLocal() {
		return url != null && url.getProtocol().equals("file");
	}

	public boolean isSelfMade() {
		return url == null;
	}

	public boolean isEditable() {
		return isSelfMade();
	}

	public boolean hasChanged() {
		return isNew || urlHasChanged() || fileHasChanged();
	}

	public boolean urlHasChanged() {
		return oldUrl != null;
	}

	public boolean fileHasChanged() {
		return oldFile != null;
	}

}
