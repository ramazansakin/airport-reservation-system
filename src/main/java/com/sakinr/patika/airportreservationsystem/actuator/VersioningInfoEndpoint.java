package com.sakinr.patika.airportreservationsystem.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "release-notes")
public class VersioningInfoEndpoint {

    String version2 = "** Version 2.0 ** \n\n"
            + "## Added\n"
            + "* Homepage added \n"
            + "* Item creation form added \n"
            + "* View the watchlsit page added \n"
            + "## Fixed \n"
            + "* docker integration problem solved \n"
            + "## Updated \n"
            + "* /info actuator api updated with details";

    String version3 = "** Version 3.0 ** \n\n"
            + "## Added\n"
            + "* Info added \n"
            + "## Fixed \n"
            + "* airport service /getOne bug fixed \n"
            + "## Updated \n"
            + "* passenger controller base url updated";

    @ReadOperation
    public String releaseNotes() {
        return version3;
    }

    @ReadOperation
    public String selectReleaseNotes(@Selector String selector) {
        if ("2.0".equals(selector))
            return version2;
        else if ("3.0".equals(selector))
            return version3;
        else return releaseNotes();
    }

}
