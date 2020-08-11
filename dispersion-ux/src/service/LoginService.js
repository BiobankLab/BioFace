import backend from '../utils/backend'

export default class LoginService {

  static login = (loginData) => {
    return backend.post('login/', loginData)
  };

}
