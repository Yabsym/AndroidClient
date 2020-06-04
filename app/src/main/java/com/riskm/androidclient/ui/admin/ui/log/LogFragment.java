package com.riskm.androidclient.ui.admin.ui.log;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.alibaba.fastjson.JSON;
import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.data.table.TableData;
import com.riskm.androidclient.R;
import com.riskm.androidclient.entity.Log;
import com.riskm.androidclient.util.CallBackUtil;
import com.riskm.androidclient.util.RealResponse;
import com.riskm.androidclient.util.SessionRecord;
import com.riskm.androidclient.util.UrlHttpUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class LogFragment extends Fragment {
    private SmartTable tableLog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_log, container, false);
        tableLog = root.findViewById(R.id.tableLog);
        Map<String, String> headMap = new HashMap<>();
        Map<String, String> paramMap = new HashMap<>();

        //TODO  分页
        paramMap.put("page", Integer.toString(1));
        paramMap.put("limit", Integer.toString(20));
        headMap.put("Cookie", SessionRecord.getCookie());
        UrlHttpUtil.post("http://10.0.3.2:8080/admin/getListDatLog", paramMap, headMap, new CallBackUtil.CallBackDefault() {

            @Override
            public void onFailure(int code, String errorMessage) {
                System.out.print(errorMessage);
            }

            @Override
            public void onResponse(RealResponse response) {
                try {
                    StringBuffer sbf = new StringBuffer();
                    BufferedReader reader;
                    String strRead = null;
                    reader = new BufferedReader(new InputStreamReader(response.inputStream, StandardCharsets.UTF_8));
                    while ((strRead = reader.readLine()) != null) {
                        sbf.append(strRead);
                        sbf.append("\r\n");
                    }

                    reader.close();
                    Map map = JSON.parseObject(sbf.toString());
                    String tmp = map.get("data").toString();
                    tmp = tmp.substring(1, tmp.length() - 1);
                    String[] strArray = tmp.split("[}]");
                    List<String> strList = new ArrayList<>();
                    for (String str :
                            strArray) {
                        strList.add(str.substring(1) + "}");
                    }
                    strList.set(0, strArray[0] + "}");

                    Column<Integer> logID = new Column<>("日志流水号", "logID");
                    Column<String> context = new Column<>("操作内容", "context");
                    Column<String> operator = new Column<>("操作人", "operator");
                    Column<Date> time = new Column<>("时间", "time");
                    Column<String> type = new Column<>("类型", "type");
                    TableData<Log> tableData = new TableData<>("日志表", Log.covertLog(strList), logID, context, operator, time, type);

                    tableLog.setTableData(tableData);
                    tableLog.getConfig().setContentStyle(new FontStyle(50, Color.RED));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return root;
    }
}