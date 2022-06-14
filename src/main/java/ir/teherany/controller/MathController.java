package ir.teherany.controller;

import org.apache.commons.math3.stat.StatUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
public class MathController {
    @GetMapping("/say/{name}")
    public String sayHi(@PathVariable String name) {
        return "Hi " + name;
    }

    @PostMapping("/calc")
    public double calc(@RequestBody NumController numController) {
        double result = 0;
        if (numController.getOperation().equals("+"))
            result = numController.getFirstNumber() + numController.getSecondNumber();
        else if (numController.getOperation().equals("-"))
            result = numController.getFirstNumber() - numController.getSecondNumber();
        else result = 0;
        return result;
    }

    @PostMapping("/math")
    public double math(@RequestBody List<Double> list, String opt) {
        double[] doubles = new double[list.size()];
        double result = 0;
        for (int i = 0; i < list.size(); i++)
            doubles[i] = list.get(i);
        if (opt.equals("min".toLowerCase(Locale.ROOT))) {
            double min = StatUtils.min(doubles);
            result = min;
        } else if (opt.equals("max".toLowerCase(Locale.ROOT))) {
            double max = StatUtils.max(doubles);
            result = max;
        } else if (opt.equals("mean".toLowerCase(Locale.ROOT))) {
            double mean = StatUtils.mean(doubles);
            result = mean;
        }
        return result;
    }
}
