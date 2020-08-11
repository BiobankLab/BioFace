import backend from "../utils/backend";

export default class BiobankPermissionService {
  static getBiobanks = () => {
    return backend.get("permissions/biobanks");
  };
  static addBiobank = ({ biobankName, biobankKey }) => {
    return backend.post("permissions/addBiobank", { biobankName, biobankKey });
  };
  static updateBiobankAccession = req => {
    return backend.post("permissions/biobank/accession", req);
  };
  static deleteBiobank = req => {
    return backend.post("permissions/deleteBiobank", req);
  };
}
