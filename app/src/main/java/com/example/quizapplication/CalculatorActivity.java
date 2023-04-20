package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CalculatorActivity extends AppCompatActivity {

    private AppCompatButton clear, exp, bracket, divide, seven, eight, nine, product, four, five, six, minus;
    private AppCompatButton one, two, three, add, decimal, zero, equal;
    private TextView workingScreen, resultsScreen;

    private String workings = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        clear = findViewById(R.id.clear);
        exp = findViewById(R.id.exp);
        bracket = findViewById(R.id.bracket);
        divide = findViewById(R.id.divide);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        product = findViewById(R.id.product);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        minus = findViewById(R.id.minus);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        add = findViewById(R.id.add);
        decimal = findViewById(R.id.decimal);
        zero = findViewById(R.id.zero);
        equal = findViewById(R.id.equal);
        workingScreen = findViewById(R.id.working);
        resultsScreen = findViewById(R.id.results);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWorkings("1");
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWorkings("2");
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWorkings("3");
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWorkings("4");
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWorkings("5");
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWorkings("6");
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWorkings("7");
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWorkings("8");
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWorkings("9");
            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWorkings("0");
            }
        });

        decimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWorkings(".");
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWorkings("+");
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWorkings("-");
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWorkings("/");
            }
        });

        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWorkings("*");
            }
        });

        exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWorkings("^");
            }
        });

        bracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int leftBrackets = countChar(workings, '(');
                int rightBrackets = countChar(workings, ')');

                if(leftBrackets == rightBrackets) {
                    setWorkings("(");
                } else {
                    setWorkings(")");
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                workings = "";
                workingScreen.setText("");
                resultsScreen.setText("");
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double result = null;

                ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");

                try {
                    result = (double)engine.eval(workings);
                } catch (ScriptException error) {
                    Toast.makeText(CalculatorActivity.this, "Invalid Input", Toast.LENGTH_SHORT).show();
                }

                if (result != null)
                    resultsScreen.setText(String.valueOf(result.doubleValue()));
            }
        });
    }

    private void setWorkings(String incoming) {
        workings = workings + incoming;
        workingScreen.setText(workings);
    }

    public int countChar(String str, char c)
    {
        int count = 0;

        for(int i=0; i < str.length(); i++)
        {    if(str.charAt(i) == c)
            count++;
        }
        return count;
    }
}
