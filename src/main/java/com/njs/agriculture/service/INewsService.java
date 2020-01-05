package com.njs.agriculture.service;

import com.alibaba.fastjson.JSONObject;
import com.njs.agriculture.common.ServerResponse;

public interface INewsService {
  ServerResponse add(JSONObject jsonObject);
  ServerResponse delete(int id);
  ServerResponse getAll();
  ServerResponse updateIntrodution(JSONObject jsonObject);
  ServerResponse getIntrodution();
}
