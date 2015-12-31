package net.masterthought.cucumber;

import static net.masterthought.cucumber.FileReaderUtil.getAbsolutePathFromResource;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import net.masterthought.cucumber.json.Feature;
import net.masterthought.cucumber.json.Element;
import net.masterthought.cucumber.json.support.TagObject;

public class TagsTest {

    private ReportInformation reportInformation;

    @Before
    public void setUpJsonReports() throws IOException {
        List<String> jsonReports = new ArrayList<String>();
        jsonReports.add(getAbsolutePathFromResource("net/masterthought/cucumber/tags.json"));
        List<Feature> features = new ReportParser().parseJsonResults(jsonReports);
        reportInformation = new ReportInformation(features);
    }

    @Test
    public void shouldGetTotalTagSteps() {
        assertThat(reportInformation.getAllTagSteps(), is(4));
    }

    @Test
    public void shouldGetTotalTagPasses() {
        assertThat(reportInformation.getAllPassesTags(), is(2));
    }

    @Test
    public void shouldGetTotalTagFails() {
        assertThat(reportInformation.getAllFailsTags(), is(2));
    }

    @Test
    public void shouldGetTotalTagSkipped() {
        assertThat(reportInformation.getAllSkippedTags(), is(0));
    }

    @Test
    public void shouldGetTotalTagPending() {
        assertThat(reportInformation.getAllPendingTags(), is(0));
    }

    @Test
    public void shouldGetTotalTagScenarios() {
        assertThat(reportInformation.getAllTagScenarios(), is(4));
    }

    @Test
    public void shouldgetTotalTagScenariosPassed() {
        assertThat(reportInformation.getAllPassedTagScenarios(), is(2));
    }

    @Test
    public void shouldgetTotalTagScenariosFailed() {
        assertThat(reportInformation.getAllFailedTagScenarios(), is(2));
    }

    @Test
    public void shouldGetTagInfoForTag1() {
        TagObject tagObject = reportInformation.getAllTags().get(0);
        assertThat(tagObject.getTagName(), is("@tag1"));
    }

    @Test
    public void shouldGetTagInfoForTag2() {
        TagObject tagObject = reportInformation.getAllTags().get(1);
        assertThat(tagObject.getTagName(), is("@tag2"));
    }

    @Test
    public void shouldGetTagScenariosForTag1() {
        List<Element> elements = reportInformation.getAllTags().get(0).getElements();
        assertThat(elements.size(), is(2));
        Element firstElement = elements.get(0);
        Element secondElement = elements.get(1);
        assertThat(firstElement.getRawName(), is("scenario1 for tag1"));
        assertThat(secondElement.getRawName(), is("scenario2 for tag1"));
    }

    @Test
    public void shouldGetTagScenariosForTag2() {
        List<Element> elements = reportInformation.getAllTags().get(1).getElements();
        assertThat(elements.size(), is(2));
        Element firstElement = elements.get(0);
        Element secondElement = elements.get(1);
        assertThat(firstElement.getRawName(), is("scenario1 for tag2"));
        assertThat(secondElement.getRawName(), is("scenario2 for tag2"));
    }
}
