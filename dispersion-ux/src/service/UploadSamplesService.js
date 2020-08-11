import backend from "../utils/backend";

export default class UploadSamplesService {
  static uploadSamples = file => {
    return backend.post("/dispersionSample/addSamples", file);
  };

  static job = file => {
    return backend.post("/dispersionJob/", file);
  };
}
