package com.riskm.androidclient.ui.admin.ui.score;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.alibaba.fastjson.JSON;
import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.data.table.TableData;
import com.riskm.androidclient.R;
import com.riskm.androidclient.entity.CourseInf;
import com.riskm.androidclient.util.CallBackUtil;
import com.riskm.androidclient.util.RealResponse;
import com.riskm.androidclient.util.SessionRecord;
import com.riskm.androidclient.util.UrlHttpUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ScoreFragment extends Fragment {
    static int page = 1;
    private SmartTable tableScore;
    private Button button_next;
    private Button button_prev;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_score, container, false);
        tableScore = root.findViewById(R.id.tableScore);
        createTable();
        button_prev = root.findViewById(R.id.prevScore);
        button_next = root.findViewById(R.id.nextScore);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++page;
                createTable();
            }
        });
        button_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page > 0) {
                    --page;
                    createTable();
                }
            }
        });
        return root;
    }

    private void createTable() {
        Map<String, String> headMap = new HashMap<>();
        Map<String, String> paramMap = new HashMap<>();
        //TODO  分页
        paramMap.put("page", Integer.toString(page));
        paramMap.put("limit", Integer.toString(20));
        headMap.put("Cookie", SessionRecord.getCookie());
        UrlHttpUtil.post("http://10.0.3.2:8080/admin/getListDatCourseInf", paramMap, headMap, new CallBackUtil.CallBackDefault() {

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
                    Column<String> courseInfID = new Column<>("流水号", "courseInfID");
                    Column<String> studentName = new Column<>("学生名", "studentName");
                    Column<Date> courseName = new Column<>("课程名", "courseName");
                    Column<Date> score = new Column<>("成绩", "score");
                    Column<String> studentAccount = new Column<>("学生账号", "studentAccount");
                    Column<Integer> courseID = new Column<>("课程号", "courseID");
                    TableData<CourseInf> tableData = new TableData<>("成绩表", CourseInf.conventCourseInf(strList), studentName, courseName, score, studentAccount, courseID, courseInfID);

                    tableScore.setTableData(tableData);
                    tableScore.getConfig().setContentStyle(new FontStyle(50, Color.RED));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
