package org.iskcon.icc.lavamatra.service;

import org.iskcon.icc.lavamatra.Model.MediaModel;

import java.util.List;

/**
 * Created by Ankush on 25-05-2017.
 */

public interface AsyncResponse {
    void processFinish(List<MediaModel> mediaModelList);
}
