package pl.edu.pjwstk.jaz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

@RestController
public class AverageController {
    @GetMapping("average")
    public AverageResult getAverage(@RequestParam(value = "numbers", required = false) String numbers)
    {
        //numbers...
        //numbers jest stringiem, trzeba go rozdzielic po przecinku i parseInt wszystkie liczby, potem z liczb policzyc sredia, zeby
        //w wyniku nie bylo zer na koncu

        if(numbers == null)
        {
            return new AverageResult("Please put parameters.");
        }
        else
        {
            float sum = 0;
            int counter = 0;
            String[] tab = numbers.split(",");
            int[] nums = new int[tab.length];

            for(int i = 0; i < tab.length; i++)
            {
                nums[i] = Integer.parseInt(tab[i]);
                sum += nums[i];
                counter++;
            }

            BigDecimal bd = new BigDecimal(sum/counter);
            bd = bd.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();

            return new AverageResult("Average equals: " + bd);
        }

    }
}
