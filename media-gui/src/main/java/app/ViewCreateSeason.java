package app;

import org.jbonnet.bean.form.DefaultFieldContexts;
import org.jbonnet.bean.form.DefaultManipulator;
import org.jbonnet.bean.form.FxObjectFiller;
import org.jbonnet.bean.form.FxViewFactory;

import app.converter.StringConverterBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import model.Season;
import model.SeasonPK;
import model.Sery;

public class ViewCreateSeason extends VBox {

	private Season season;
	private Repositories repositories;

	public ViewCreateSeason(Repositories repositories) {
		this.repositories = repositories;
		initView();
	}

	private void initView() {

		DefaultFieldContexts fieldMapping = new DefaultFieldContexts(Season.class, "id");
		GridPane gridPane = new GridPane();
		gridPane.setHgap(15);
		gridPane.setVgap(15);
		Node fillViewComponent = FxViewFactory.fillViewComponent(Season.class, fieldMapping, new DefaultManipulator(),
				gridPane);
		Integer lastRowIndex = GridPane.getRowIndex(fillViewComponent);
		Label l = new Label("saison n°");
		TextField fieldSaisonNumber = new TextField();
		gridPane.add(l, 0, lastRowIndex + 1);
		gridPane.add(fieldSaisonNumber, 1, lastRowIndex + 1);
		Label lSerie = new Label("serie");
		repositories.getSeriesRepository().findAll();
		ObservableList<Sery> items = FXCollections
				.observableArrayList(repositories.getSeriesRepository().findAllOrderByNameAsc());
		ComboBox<Sery> comboBox = new ComboBox<>(items);
		StringConverter<Sery> converter = StringConverterBuilder.build(Sery::getName);
		comboBox.setButtonCell(new TextFieldListCell<>(converter));
		comboBox.setCellFactory(TextFieldListCell.forListView(converter));
		gridPane.add(lSerie, 0, lastRowIndex + 2);
		gridPane.add(comboBox, 1, lastRowIndex + 2);
		Button buttonSave = new Button("save");

		ObservableList<Node> boxChildren = this.getChildren();

		season = new Season();

		boxChildren.addAll(gridPane, buttonSave);

		buttonSave.setOnAction((a) -> {
			FxObjectFiller.fillObject(season, this);

			season.setSeries(comboBox.selectionModelProperty().get().getSelectedItem());

			try {
				Integer valueOf = Integer.valueOf(fieldSaisonNumber.getText());
				season.getId().setSeasonNumber(valueOf);
				season = repositories.getSeasonRepository().save(season);
			} catch (Exception e) {
			}

		});

	}
}
