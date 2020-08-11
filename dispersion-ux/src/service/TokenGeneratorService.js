import backend from "../utils/backend";

export default class TokenGeneratorService {
  static generateToken = () => {
    return backend.get("generateToken/generate");
  };
}
